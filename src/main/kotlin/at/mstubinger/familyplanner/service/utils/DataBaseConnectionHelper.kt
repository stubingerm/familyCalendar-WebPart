package at.mstubinger.familyplanner.service.utils

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.LoggedInUser
import java.sql.*
import java.util.*

class DataBaseConnectionHelper {

    private val connectionProps = Properties()
    private lateinit var conn:Connection


    init{
        connectionProps.put("user","stubinger")
        connectionProps.put("password","Alexander23.09.2007")

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://"+
                            "95.217.133.168:3306/APP_FAMILY_CAL",
                    connectionProps
            )
        } catch (ex: SQLException){
            //handling of any sql errors
            println(ex)
        } catch (ex: Exception){
            //handle any other exceptions
            ex.printStackTrace()
        }

    }

    fun query(query:String): ResultSet? {

        var stmt: Statement? =null
        var resultset: ResultSet? = null

        try{
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeQuery(query)

            if (stmt.execute(query)){
                resultset = stmt.resultSet
            }
        }catch (ex: SQLException){
            //handle sql exceptions
            ex.printStackTrace()
        }
        return resultset
    }

    fun uniqueCheck(query: String, column:String):Int{
        var resultSet = query(query)

        println("do unique check. Query: $query")

        var rows = 0
        var count:Int = -1

        if (resultSet!!.next()){
            count = resultSet.getInt(column)
        }
        return count
    }

    fun update(query: String){

        val result = conn!!.createStatement().executeUpdate(query)

    }

    fun getLastInsertedId(): Int {

        val query = "SELECT LAST_INSERT_ID() AS LAST_INSERTED_ID"

        var lastInsertedId = -1

        println("get last inserted id...")
        var resultSet = query(query)

        if (resultSet!!.next()){
            lastInsertedId=resultSet.getInt("LAST_INSERTED_ID")
        }

        return lastInsertedId

    }


}