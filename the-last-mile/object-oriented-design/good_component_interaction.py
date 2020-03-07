class Storage:
    def dispatch(self, order):
        pass

    def dispatchAll(self, dispatcher):
        manifest = dispatcher.getManifest()
        for order in manifest:
            self.dispatch(order)


class Dispatcher:
    def __init__(self) -> None:
        self.dispatchTable = []

    def deliveryRequest(self, order):
        self.dispatchTable.append(order)

    def getManifest(self):
        manifest = []
        # prepare a manifest out of the dispatch table
        return manifest


class System:
    def __init__(self) -> None:
        self.storage = Storage()
        self.dispatcher = Dispatcher()

    def run(self):
        self.storage.dispatchAll(self.dispatcher)
