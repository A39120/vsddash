package pt.isel.vsdashbapi

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class GetUserTest {

    val logger = LoggerFactory.getLogger(GetUserTest::class.java)

    @Test
    fun getUser(){
        val user = ConnectionToApiTest().connectToApi()
        Assert.assertNotNull(user.user.enterpriseID)
        user.user.run {
            logger.info("Enterprise ID: $enterpriseID, enterprise name: $enterpriseName")
            logger.info("User: $userName")
            logger.info("Role: $role, entity scope: $entityScope")
        }
    }
}