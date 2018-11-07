    ...
    List<Animal> list = ...
    list.add(guess(sound));
    ...
}

Animal guess(sound) {
    if (sound == null)
        return new MuteAnimal();
    ...
    if (soundsNeigh(sound))
        return new Horse();
    Amplifier amp =
      new Amplifier();
    if (soundsHiss(
      amp.apply(sound)))
        return new Snake();
    if (soundsBuzz(
      amp.apply(sound)))
        return new Bee();
    return new Mystery();
}
