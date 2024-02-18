from pika.adapters.blocking_connection import BlockingChannel


class Consumer:
    def __init__(self, channel: BlockingChannel):
        self.channel = channel
