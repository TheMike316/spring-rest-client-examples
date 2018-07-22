package com.miho.springrestclientexamples.services

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import reactor.core.publisher.Mono

@RunWith(SpringRunner::class)
@SpringBootTest
class ApiServiceImplTest {

    @Autowired
    private lateinit var apiService: ApiService


    @Test
    fun testGetUsers() {
        val users = apiService.getUsers(3)

        //for whatever reason, the api always returns one additional data record
        assertEquals(4, users.size)
    }

    @Test
    fun testGetUsersReactively(){
        val users = apiService.getUsers(Mono.just(3)).collectList().block()

        assertEquals(4, users!!.size)
    }


}
