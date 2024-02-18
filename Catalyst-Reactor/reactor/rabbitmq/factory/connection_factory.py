from reactor.model.factory import AbstractFactory
from pika import BlockingConnection, ConnectionParameters, PlainCredentials


class RabbitMqConnectionFactory(AbstractFactory):
    connection: BlockingConnection | None = None

    def create(self) -> BlockingConnection | None:
        connection_params = ConnectionParameters(
            host="localhost", port=5672, virtual_host="/",
            credentials=PlainCredentials("guest", "guest")
        )
        self.connection = BlockingConnection(connection_params)
        return self.connection
