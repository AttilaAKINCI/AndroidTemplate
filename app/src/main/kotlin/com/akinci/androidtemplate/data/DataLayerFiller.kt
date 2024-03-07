package com.akinci.androidtemplate.data

// TODO use data folder for data layer specific files:
//  repository, data classes (local + rest), ROOM db, data store, data mappers or etc.

/**
 *  Notes for inner structure of data layer:
 *
 *  - Repositories should be responsible to data related operations and their delivery
 *  - Repositories should provide safe returns. (runCatching api can be used on the functions which
 *    throws exceptions)
 *  - Rest related data classes should be implement Serializable interface. (Json parsers of REST
 *    libraries requires a serializer: Gson, Kotlin Serializer or etc.)
 * **/
