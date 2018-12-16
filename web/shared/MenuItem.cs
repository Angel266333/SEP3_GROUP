using System.Runtime.Serialization;

namespace web
{
    [DataContract]
    public class MenuItem
    {
        [DataMember]
        public int id;
        [DataMember]
        public string name;
        [DataMember]
        public string description;
        [DataMember]
        public string[] ingredients;
        [DataMember]
        public bool isAvailable;
        [DataMember]
        public int price;
    }
}