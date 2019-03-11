using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System;

namespace web.Pages
{
    public class Receipt : PageModel
    {
        public string orderId{get; set;}
        public string cartLogo{get; set;}

        public void OnGet()
        {
            string s;
            bool b = Request.Cookies.TryGetValue("orderid", out s);
            if(!b) {
                orderId = "Not found";
            }
            int i = Int32.Parse(s);
            orderId = "" + i;

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