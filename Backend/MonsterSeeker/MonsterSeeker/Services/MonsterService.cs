using MonsterSeeker.Dtos;
using MonsterSeeker.Entities;
using MonsterSeeker.Refit;
using MonsterSeeker.ValueObjects;
using Refit;

namespace MonsterSeeker.Services
{
    public class MonsterService : IMonsterService
    {
        Dnd5eApi _dnd5eApi;

        List<MonsterEntity> _monsters;

        public MonsterService()
        {
            _dnd5eApi = RestService.For<Dnd5eApi>("https://www.dnd5eapi.co/api/");

            _monsters = new List<MonsterEntity>()
            {
                new MonsterEntity{ Id = 0, Name = "Test3", Description = "Desc", Favourite = false },
                new MonsterEntity{ Id = 1, Name = "Test2", Description = "Desc", Favourite = true },
                new MonsterEntity{ Id = 2, Name = "Test1", Description = "Desc", Favourite = false },
            };
        }

        public async Task<List<MonsterEntity>> FetchMonsters(CancellationToken cancellationToken)
        {
            return _monsters;
        }

        public async Task<List<ListMonster>> GetMonsters(CancellationToken cancellationToken) 
        {
            return await GetMonstersHelper(cancellationToken);
        }

        private async Task<List<ListMonster>> GetMonstersHelper(CancellationToken cancellationToken) 
        {
            return _monsters.Select(x => new ListMonster { Name = x.Name, Favourite = x.Favourite })
                .OrderBy(x => x.Name)
                .ToList();
        }

        public async Task<Monster> GetMonster(string name, CancellationToken cancellationToken)
        {
            return await GetMonsterHelper(name, cancellationToken);
        }

        private async Task<Monster> GetMonsterHelper(string name, CancellationToken cancellationToken)
        {
            return _monsters.Select(x => new Monster { Name = x.Name, Description = x.Description }).First(x => x.Name == name);
        }

        public async Task FavouriteMonster(string name, CancellationToken cancellationToken)
        {
            await FavouriteMonsterHelper(name, cancellationToken);
        }

        private async Task FavouriteMonsterHelper(string name, CancellationToken cancellationToken)
        {
            var actualMonster = _monsters.First(x => x.Name == name);

            if (actualMonster == null)
                throw new Exception();

            actualMonster.Favourite = !actualMonster.Favourite;

            var newMonsters = _monsters.Where(x => x.Name != name).ToList();

            newMonsters.Add(actualMonster);

            _monsters = newMonsters; 
        }

        public async Task AddMonster(NewMonster monster, CancellationToken cancellationToken)
        {
            await AddMonsterHelepr(monster, cancellationToken);
        }

        private async Task AddMonsterHelepr(NewMonster monster, CancellationToken cancellationToken)
        {
            if (_monsters.Any(x => x.Name == monster.Name))
                throw new Exception();

            var maxId = _monsters.Max(x => x.Id);

            var newMonster = new MonsterEntity { Id = maxId + 1, Name = monster.Name, Description = monster.Description, Favourite = false };

            _monsters.Add(newMonster);
        }

        public async Task DeleteMonster(string name, CancellationToken cancellationToken)
        {
            await DeleteMonsterHelper(name, cancellationToken);
        }

        private async Task DeleteMonsterHelper(string name, CancellationToken cancellationToken)
        {
            if (!_monsters.Any(x => x.Name == name))
                throw new Exception();

            var newMonsters = _monsters.Where(x => x.Name != name).ToList();

            _monsters = newMonsters;
        }
    }
}
