...
List<Animal> collection = ...
Animal animal = null;
if (sound == null)
    animal = new DumbAnimal();
if (soundsLikeTrumpet(sound))
    animal = new Elephant();
else if (soundsLikeNeigh(sound))
    animal = new Horse();
else if (soundsLikeHiss(amplify(sound)))
    animal = new Snake();
else if (soundsLikeBuzz(amplify(sound)))
    animal = new Bee();
else
    animal = new UnknownAnimal();
collection.add(animal);
...
