using System;
using BuberBreakfast.Contracts.Breakfast;
using BuberBreakfast.Models;
using BuberBreakfast.ServerErrors;
using BuberBreakfast.Services.Breakfast;
using ErrorOr;
using Microsoft.AspNetCore.Mvc;

[ApiController]
[Route("[controller]")]
public class BreakfastController : ControllerBase
{

    private readonly IBreakfastService _breakfastService;

    public BreakfastController(IBreakfastService breakfastService)
    {
        _breakfastService = breakfastService;
    }

    [HttpPost]
    public IActionResult CreateBreakfast(CreateBreakfastRequest request)
    {
        var breakfast = new Breakfast(
            Guid.NewGuid(),
            request.Name,
            request.Description,
            request.StartDateTime,
            request.EndDateTime,
            DateTime.UtcNow,
            request.Savory,
            request.Sweet);


        _breakfastService.CreateBreakfast(breakfast);
        


        var resposne = new BreakfastResponse(
            breakfast.Name,
            breakfast.Description,
            breakfast.StartDateTime,
            breakfast.EndDateTime,
            breakfast.LastModifiedDateTime,
            breakfast.Savory,
            breakfast.Sweet
        );

        return CreatedAtAction(
            actionName: nameof(GetBreakfast),
            routeValues: new { id = breakfast.ID },
            value: resposne);
    }

    [HttpGet("{id:guid}")]
    public IActionResult GetBreakfast(Guid id)
    {
        ErrorOr<Breakfast> getBreakfastResult = _breakfastService.GetBreakfast(id);

        if(getBreakfastResult.IsError && getBreakfastResult.FirstError == Errors.Breakfast.NotFound){
            return NotFound();
        }
        var breakfast = getBreakfastResult.Value;

        var response = new BreakfastResponse(breakfast.Name,
        breakfast.Description,
        breakfast.StartDateTime, breakfast.EndDateTime,
        breakfast.LastModifiedDateTime,
        breakfast.Savory,
        breakfast.Sweet);

        

        return Ok(response);

    }

    [HttpPut("{id:guid}")]
    public IActionResult UpdateBreakfast(Guid id, UpdateBreakfastRequest request)
    {
        Breakfast breakfast = new Breakfast(
            id,
            request.Name,
            request.Description,
            request.StartDateTime,
            request.EndDateTime,
            DateTime.UtcNow,
            request.Savory,
            request.Sweet);

       _breakfastService.UpdateBreakfast(breakfast);
        
        return NoContent();
    }

    [HttpDelete("{id:guid}")]
    public void DeleteBreakfast(Guid id)
    {
        _breakfastService.DeleteBreakfast(id);
    }


}