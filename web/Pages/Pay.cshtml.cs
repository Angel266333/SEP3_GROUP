using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Collections.Generic;
using Microsoft.Extensions.Primitives;
using System.Text;
using System;
using Newtonsoft.Json;

namespace web.Pages
{
    public class Pay : PageModel
    {
        public IActionResult OnPost()
        {
            List<int> items = new List<int>();
            StringValues sv;
            Request.Form.TryGetValue("item", out sv);
            foreach(string str in sv.ToArray())
            {
                items.Add(Int32.Parse(str));
            }
            Order o = new Order();
            o.comment = "";
            o.idTable = 1;
            o.id = -1;
            o.receipt = "";
            o.status = "PENDING";
            o.items = items.ToArray();
            string os = JsonConvert.SerializeObject(o);
            SocketSession ss = new SocketSession();
            ss.Send("SUBMITORDER|" + os);
            string r = ss.Receive();
            Console.WriteLine(r);
            Console.WriteLine(os);
            Response.Cookies.Delete("orderid");
            Response.Cookies.Append("orderid", r);
            Response.Cookies.Delete("cart");
            return RedirectToPage("/Receipt");
        }
    }
}