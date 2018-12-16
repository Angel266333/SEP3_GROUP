using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace web.Pages
{
    public class IndexModel : PageModel
    {
        public MenuItem[] menuItems {get; set;}
        public void OnGet()
        {
            MenuItem m1 = new MenuItem();
            m1.name = "pizza";
            m1.isAvailable = true;
            m1.description = "Disgusting pizza";
            m1.price = 200;

            MenuItem m2 = new MenuItem();
            m2.name = "Shoe";
            m2.isAvailable = true;
            m2.description = "Tasty shoe";
            m2.price = 100;

            MenuItem m3 = new MenuItem();
            m3.name = "Potato";
            m3.isAvailable = true;
            m3.description = "Croatian potato";
            m3.price = 999;

            menuItems = new MenuItem[]{m1, m2, m3};

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
                dynamic d = JsonConvert.DeserializeObject(ms);
                MenuItem m = new MenuItem();
                m.id = d["id"];
                m.description = d["description"];
                m.isAvailable = d["isAvailable"];
                m.name = d["name"];
                m.price = d["price"];
                m.ingredients = new string[]{""};
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
