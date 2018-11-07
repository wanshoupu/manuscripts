...
List<Animal> collection = ...
Animal animal = null;
if (sound == null)
    return new DumbAnimal();
else if (soundsLikeNeigh(sound))
    animal = new Horse();
else if (soundsLikeHiss(sound))
    animal = new Snake();
else if (soundsLikeBuzz(sound))
    animal = new Bee();
else
    animal = new UnknownAnimal();
collection.add(animal);
...
