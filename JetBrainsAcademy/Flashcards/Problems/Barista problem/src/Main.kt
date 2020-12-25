class EspressoMachine{
    var costPerServing: Float = 0F

    constructor(coffeeCapsulesCount: Int, totalCost: Float) {
        costPerServing = totalCost / coffeeCapsulesCount
    }

    constructor(coffeeBeansWeight: Float, totalCost: Float) {
        costPerServing = totalCost / (coffeeBeansWeight / 10.6F)
    }
}
// write the EspressoMachine class here