from factory.connection_factory import RabbitMqConnectionFactory
from pika.adapters.blocking_connection import BlockingChannel
from reactor.rabbitmq.consumer import Consumer
from reactor.rabbitmq.publisher import Publisher


class RabbitMqManager:
    channel: BlockingChannel | None = None
    consumer: Consumer | None = None
    publisher: Publisher | None = None

    def create_channel(self, channel_number: int = 1) -> BlockingChannel:
        connection = RabbitMqConnectionFactory().create()
        self.channel = connection.channel(channel_number)
        return self.channel
