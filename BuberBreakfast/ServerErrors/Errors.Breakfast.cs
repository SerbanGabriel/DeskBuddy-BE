using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ErrorOr;

namespace BuberBreakfast.ServerErrors
{
    public static class Errors
    {
        public static class Breakfast{
           public static Error NotFound => Error.NotFound(
            code:"Breakfast.NotFound",
            description:"Breakfast not found"
           );
        }
        
    }
}