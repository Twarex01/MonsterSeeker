using MonsterSeeker.Dtos;
using MonsterSeeker.ValueObjects;

namespace MonsterSeeker.Services
{
    public interface IMonsterService
    {
        public Task<List<ListMonster>> GetMonsters(CancellationToken cancellationToken);
        public Task<Monster> GetMonster(string name, CancellationToken cancellationToken);
        public Task FavouriteMonster(string name, CancellationToken cancellationToken);
        public Task AddMonster(NewMonster monster, CancellationToken cancellationToken);
        public Task DeleteMonster(string name, CancellationToken cancellationToken);
    }
}
