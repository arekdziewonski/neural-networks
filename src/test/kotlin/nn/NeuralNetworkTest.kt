package nn

class NeuralNetworkTest {
    @org.junit.jupiter.api.Test
    internal fun createNetwork() {
        val nn = NeuralNetwork.newNetwork(2, 1, 2, 3)
        print(nn)
    }
}