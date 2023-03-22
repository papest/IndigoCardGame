fun bill(priceList: Map<String, Int>, shoppingList: MutableList<String>): Int {
    var resultPrice = 0
    if (priceList.isEmpty()) return resultPrice
    for (product in shoppingList) {
        if (priceList.containsKey(product)) resultPrice += priceList[product]!!
    }
    return resultPrice
}