using Microsoft.AspNetCore.Mvc;
using MonsterSeeker.Dtos;
using MonsterSeeker.ValueObjects;

namespace MonsterSeeker.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class MonsterSeekerController : ControllerBase
    {
        [HttpGet]
        public Task<List<ListMonster>> GetMonsters(CancellationToken cancellationToken) 
        {
            throw new NotImplementedException();
        }

        [HttpGet("{name}")]
        public Task<ListMonster> GetMonster([FromQuery] string name, CancellationToken cancellationToken)
        {
            throw new NotImplementedException();
        }

        [HttpPut("{name}/favourite")]
        public Task FavouriteMonster([FromQuery] string name, CancellationToken cancellationToken)
        {
            throw new NotImplementedException();
        }


        [HttpPost]
        public Task AddMonster(NewMonster newMonster, CancellationToken cancellationToken)
        {
            throw new NotImplementedException();
        }

        [HttpDelete("{name}")]
        public Task DeleteMonster([FromQuery] string name, CancellationToken cancellationToken)
        {
            throw new NotImplementedException();
        }

    }
}