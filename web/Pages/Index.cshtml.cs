using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Runtime.Serialization.Json;
using System.Runtime.Serialization;
using System.IO;
using System.Text;

namespace web.Pages
{
    public class IndexModel : PageModel
    {
        public MenuItem[] menuItems {get; set;}
        public void OnGet()
        {
            SocketSession ss = new SocketSession();
            ss.Send("GETMENUITEMS");
            string r = ss.Receive();
            string[] rs = r.Split(new char[]{'|'});
            foreach(string str in rs)
            {
                Console.WriteLine(str);
            }
            menuItems = new MenuItem[rs.Length];
            int j = 0;
            foreach(string ms in rs)
            {
                DataContractJsonSerializer ds = new DataContractJsonSerializer(typeof(MenuItem));
                MenuItem m = (MenuItem) ds.ReadObject(new MemoryStream(Encoding.UTF8.GetBytes(ms)));
                menuItems[j++] = m;
            }
        }

        public IActionResult OnPost()
        {
            KeyValuePair<string, StringValues>[] values = Request.Form.ToArray();
            foreach(KeyValuePair<string, StringValues> kvp in values)
            {
                Console.WriteLine(kvp.Key);
            }
            return RedirectToPage("Index");
        }
    }
}
