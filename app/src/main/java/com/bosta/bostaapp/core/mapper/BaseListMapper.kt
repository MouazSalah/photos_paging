package com.bosta.bostaapp.core.mapper

/**
 * map [Response] returned from network to [RequestedItem] object to use it in View.
 * @param Response all response returned from api
 * @param ResponseItem is item in list in response
 * @param RequestedItem the item returned in the list you needed to (UI Item model).
 * */
interface BaseListMapper<Response, ResponseItem, RequestedItem> {
    fun mapListData(res: Response): List<RequestedItem>

    fun mapItem(res: ResponseItem?): RequestedItem
}