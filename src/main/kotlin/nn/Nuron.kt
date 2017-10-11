package nn

import java.util.*

data class Neuron(val ins: List<Double>, val outs: List<Double>) {

    companion object {
        fun newNeuron(inputsCount: Int, outputsCount: Int): Neuron {
            val random = Random()
            return Neuron((1..inputsCount).map { random.nextDouble() },
                    (1..outputsCount).map { random.nextDouble() })
        }
    }
}

data class Layer(val neurons: List<Neuron>) {

    companion object {
        private fun newLayer(neuronsCount: Int, insPerNeuron: Int, outsPerNeuron: Int): Layer {
            return Layer((1..neuronsCount).map { Neuron.newNeuron(insPerNeuron, outsPerNeuron) })
        }

        fun newInputLayer(neuronsCount: Int): Layer {
            return newLayer(neuronsCount, 1, 0)
        }

        fun newHiddenLayer(neuronsCount: Int, inputsCount: Int, outputsCount: Int): Layer {
            return Layer((1..neuronsCount).map { Neuron.newNeuron(inputsCount, outputsCount) })
        }

        fun newOutputLayer(neuronsCount: Int): Layer {
            return newLayer(neuronsCount, 0, 1)
        }
    }
}

data class NeuralNetwork(val inputLayer: Layer, val hiddenLayers: List<Layer>, val outputLayer: Layer) {

    companion object {
        fun newNetwork(inputsCount: Int, outputsCount: Int, hiddenLayersCount: Int, neuronsPerHiddenLayer: Int): NeuralNetwork {
            val hiddenLayers = arrayListOf<Layer>()
            hiddenLayers.add(Layer.newHiddenLayer(neuronsPerHiddenLayer, inputsCount,
                    if (hiddenLayersCount == 1) outputsCount else neuronsPerHiddenLayer))

            for (i in 2 until hiddenLayersCount) {
                hiddenLayers.add(Layer.newHiddenLayer(neuronsPerHiddenLayer, neuronsPerHiddenLayer, neuronsPerHiddenLayer))
            }

            hiddenLayers.add(Layer.newHiddenLayer(neuronsPerHiddenLayer, neuronsPerHiddenLayer, outputsCount))

            return NeuralNetwork(Layer.newInputLayer(inputsCount), hiddenLayers, Layer.newOutputLayer(outputsCount))
        }
    }
}
