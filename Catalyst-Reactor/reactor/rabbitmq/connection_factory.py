from pika import BlockingConnection, ConnectionParameters, PlainCredentials


class RabbitMqConnectionFactory:
    @staticmethod
    def get_connection() -> BlockingConnection:
        plain_credentials = PlainCredentials("gues", "guest")
        connection_params = [
                ConnectionParameters(host="localhost", port=5672, virtual_host="/", credentials=plain_credentials),
                ConnectionParameters(host="localhost", port=5673, virtual_host="/", credentials=plain_credentials),
                ConnectionParameters(host="localhost", port=5674, virtual_host="/", credentials=plain_credentials)
        ]
        connection = BlockingConnection(connection_params)
        return connection
