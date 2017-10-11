package nn

data class Neuron(val inWeights: List<Double>)

data class Layer(val neurons: List<Neuron>)

data class NeuralNetwork(val inputLayer: Layer, val hiddenLayers: List<Layer>, val outputLayer: Layer)
