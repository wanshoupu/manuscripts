class Storage:
    def dispatch(self, order):
        pass


class Dispatcher:
    def __init__(self) -> None:
        self.dispatchTable = []

    def deliveryRequest(self, order):
        self.dispatchTable.append(order)

    def dispatch(self, order):
        pass

    def dispatchAll(self, storage):
        for order in self.dispatchTable:
            storage.dispatch(order)
            self.dispatch(order)


class System:
    def __init__(self) -> None:
        self.storage = Storage()
        self.dispatcher = Dispatcher()

    def run(self):
        order = ...
        self.dispatcher.deliveryRequest(order)
        ...
        self.dispatcher.dispatchAll(self.storage)
