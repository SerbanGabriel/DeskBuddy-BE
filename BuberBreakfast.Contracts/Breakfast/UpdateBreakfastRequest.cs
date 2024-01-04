using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BuberBreakfast.Contracts.Breakfast
{
   public record UpdateBreakfastRequest
    (
        string Name,
        string Description,
        DateTime StartDateTime,
        DateTime EndDateTime,
        List<string> Savory,
        List<string> Sweet
    );
}