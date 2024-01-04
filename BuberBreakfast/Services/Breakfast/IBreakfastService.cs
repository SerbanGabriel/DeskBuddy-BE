using BuberBreakfast.Contracts.Breakfast;
using BuberBreakfast.Models;
using ErrorOr;

public interface IBreakfastService
{
    void CreateBreakfast(Breakfast breakfast);

    ErrorOr<Breakfast> GetBreakfast(Guid id);

    void UpdateBreakfast(Breakfast request);

    void DeleteBreakfast(Guid id);
}