package at.mstubinger.familyplanner.service.utils

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
                            "95.217.133.168:3306/",
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

    fun query(query:String){

        var stmt: Statement? =null
        var resultset: ResultSet? = null

        try{
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeQuery(query)

            if (stmt.execute(query)){
                resultset = stmt.resultSet
            }

            while (resultset!!.next()){
                println(resultset)
            }

        }catch (ex: SQLException){
            //handle sql exceptions
            ex.printStackTrace()
        }

    }



}