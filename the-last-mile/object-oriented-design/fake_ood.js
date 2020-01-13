class EventConsumer extends Consumer {
    init () {
        super.connect();
        super.startConsume(()=>{...}, [...this.handledTopics]);
    }

    shutdown (): Promise<void> {
        this.stopConsumeOnInterval();
        await this.disconnect();
    }
}

class Consumer extends Client {
    connect () {...}
    subscribe (topics: string[]) {...}
    unsubscribe () {...}
    startConsume (process: Process, topics?: string[]) {...}
}
