# Inverted-Index

#### Sequence diagram 

![InvertedIndex](https://github.com/NoshinNirvana916Prachi/Inverted-Index/assets/58959257/9bbd760b-9b03-4cb4-807f-ffe551bd3aa4)

In this sequence diagram:

The RMI client looks up the RMI registry for the InvertedIndexService.
The RMI registry returns the reference to the InvertedIndexService.
The RMI client invokes the getInvertedIndex method on the service, passing the filename.
The RMI service processes the request by performing the indexing task, using either ForkJoinPool or Executors with Callable.
Once the indexing task is complete, the service returns the inverted index to the client.
The RMI client receives the inverted index and processes it to display the top 5 tokens with their locations.
