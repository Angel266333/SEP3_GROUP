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
using System.Web;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Builder;

namespace web.Pages
{
    public class IndexModel : PageModel
    {
        public MenuItem[] menuItems {get; set;}
        public string cartLogo{get; set;}

        public void OnGet()
        {
            SocketSession ss = new SocketSession();
            ss.Send("GETMENUITEMS");
            string r = ss.Receive();
            string[] rs = r.Split(new char[]{'|'});
            menuItems = new MenuItem[rs.Length];
            int j = 0;
            foreach(string ms in rs)
            {
                MenuItem m = JsonConvert.DeserializeObject<MenuItem>(ms);
                menuItems[j++] = m;
            }

            if(Request.Cookies.ContainsKey("cart"))
            {
                cartLogo = "/lib/img/cart.png";
            }
            else {
                cartLogo = "/lib/img/cart_gray.png";
            }
        }
    }
}
