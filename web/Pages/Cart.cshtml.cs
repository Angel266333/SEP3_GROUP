using System;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Collections.Generic;
using Microsoft.Extensions.Primitives;
using System.Threading.Tasks;
using Newtonsoft.Json;
using System.Text;

namespace web.Pages
{
    public class Cart : PageModel
    {
        public MenuItem[] cartItems{get; set;}
        public int totalSum{get; set;}
        public string cartLogo{get; set;}

        public void OnGet()
        {
            if(Request.Cookies.ContainsKey("cart"))
            {
                cartLogo = "/lib/img/cart.png";
            }
            else {
                cartLogo = "/lib/img/cart_gray.png";
            }
            

            SocketSession ss = new SocketSession();
            ss.Send("GETMENUITEMS");
            string r = ss.Receive();
            string[] rs = r.Split(new char[]{'|'});
            MenuItem[] allItems = new MenuItem[rs.Length];
            int j = 0;
            foreach(string str in rs)
            {
                allItems[j++] = JsonConvert.DeserializeObject<MenuItem>(str);
            }

            string c;
            bool b = Request.Cookies.TryGetValue("cart", out c);
            if(!b)
            {
                cartItems = new MenuItem[0];
                return;
            }
            string[] cs = c.Split(new char[]{','});
            cartItems = new MenuItem[cs.Length];
            j = 0;
            totalSum = 0;
            foreach(string str in cs)        
            {
                int i = Int32.Parse(str);
                foreach(MenuItem mi in allItems)
                {
                    if(mi.id == i)
                    {
                        cartItems[j++] = mi;
                        totalSum += mi.price;
                    }
                }
            }
        }

        public IActionResult OnPost()
        {
            StringBuilder sb = new StringBuilder();
            foreach(KeyValuePair<string, StringValues> kvp in Request.Form)
            {
                if(!kvp.Key.Equals("__RequestVerificationToken") && kvp.Value.Equals("on"))
                {
                    sb.Append(kvp.Key);
                    sb.Append(',');
                }
            }
            Response.Cookies.Delete("cart");
            sb.Remove(sb.Length - 1, 1);
            Response.Cookies.Append("cart", sb.ToString());
            return RedirectToPage("/Cart");
        }
    }
}