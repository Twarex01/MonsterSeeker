using MonsterSeeker.ValueObjects;
using Refit;

namespace MonsterSeeker.Refit
{

    //https://www.dnd5eapi.co/api/
    public interface Dnd5eApi
    {
        [Get("/monsters")]
        Task<List<ListMonster>> GetMonsters();
    }
}
