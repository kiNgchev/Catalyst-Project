from pika.adapters.blocking_connection import BlockingChannel


class Publisher:
    def __init__(self, channel: BlockingChannel):
        self.channel = channel
