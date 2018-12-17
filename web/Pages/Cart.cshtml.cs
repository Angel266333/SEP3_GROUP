using System;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Collections.Generic;
using Microsoft.Extensions.Primitives;

namespace web.Pages
{
    public class Cart : PageModel
    {
        public MenuItem[] cartItems{get; set;}

        public void OnGet()
        {
        }
    }
}