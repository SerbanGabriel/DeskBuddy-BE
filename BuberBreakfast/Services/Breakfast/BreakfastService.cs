using BuberBreakfast.Contracts.Breakfast;
using BuberBreakfast.Models;
using BuberBreakfast.ServerErrors;
using ErrorOr;

namespace BuberBreakfast.Services.Breakfast
{
    public class BreakfastService : IBreakfastService
    {
        public static readonly Dictionary<Guid, Models.Breakfast> _breakfasts = new();

        public void CreateBreakfast(Models.Breakfast breakfast)
        {
            _breakfasts.Add(breakfast.ID, breakfast);
        }

        public void DeleteBreakfast(Guid id)
        {
            _breakfasts.Remove(id);
        }

        public ErrorOr<Models.Breakfast> GetBreakfast(Guid id)
        {
            if (_breakfasts.TryGetValue(id, out var breakfast))
            {
                return breakfast;
            }
            return Errors.Breakfast.NotFound;
        }

        public void UpdateBreakfast(Models.Breakfast request)
        {
            _breakfasts[request.ID] = request;
        }
    }
}