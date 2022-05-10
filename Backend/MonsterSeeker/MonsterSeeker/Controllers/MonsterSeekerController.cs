using Microsoft.AspNetCore.Mvc;
using MonsterSeeker.Dtos;
using MonsterSeeker.Services;
using MonsterSeeker.ValueObjects;

namespace MonsterSeeker.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class MonsterSeekerController : ControllerBase
    {
        IMonsterService _monsterService;

        public MonsterSeekerController(IMonsterService monsterService)
        {
            _monsterService = monsterService;
        }

        [HttpGet]
        public async Task<List<ListMonster>> GetMonsters(CancellationToken cancellationToken) 
        {
            return await _monsterService.GetMonsters(cancellationToken);
        }

        [HttpGet("{name}")]
        public async Task<Monster> GetMonster([FromRoute] string name, CancellationToken cancellationToken)
        {
            return await _monsterService.GetMonster(name, cancellationToken);
        }

        [HttpPut("{name}/favourite")]
        public async Task FavouriteMonster([FromRoute] string name, CancellationToken cancellationToken)
        {
            await _monsterService.FavouriteMonster(name, cancellationToken);
        }


        [HttpPost]
        public async Task AddMonster([FromQuery] NewMonster newMonster, CancellationToken cancellationToken)
        {
            await _monsterService.AddMonster(newMonster, cancellationToken);
        }

        [HttpDelete("{name}")]
        public async Task DeleteMonster([FromRoute] string name, CancellationToken cancellationToken)
        {
            await _monsterService.DeleteMonster(name, cancellationToken);
        }

    }
}