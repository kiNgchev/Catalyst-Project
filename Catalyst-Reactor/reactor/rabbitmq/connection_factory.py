from pika import BlockingConnection, ConnectionParameters, PlainCredentials


class RabbitMqConnectionFactory:
    @staticmethod
    def get_connection() -> BlockingConnection:
        connection_params = ConnectionParameters(
            host="localhost",
            port=5672,
            virtual_host="/",
            credentials=PlainCredentials("gues", "guest")
        )
        connection = BlockingConnection(connection_params)
        return connection
