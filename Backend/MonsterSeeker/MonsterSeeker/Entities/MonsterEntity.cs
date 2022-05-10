namespace MonsterSeeker.Entities
{
    public class MonsterEntity
    {
        public long Id { get; set; } = default!;

        public string Name { get; set; } = default!;

        public string Description { get; set; } = default!;

        public bool Favourite { get; set; } = default!;
    }
}
