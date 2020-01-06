class Producer {
  public publish(String message, String topic) {
      ...
  }
}

class Consumer {
  public consume(ConsumeStreamMessage streamMessage, String[] topics) {
    ...
  }
  public subscribe(String[] topics) {
    ...
  }
}
