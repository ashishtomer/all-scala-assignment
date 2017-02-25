package edu.knoldus

import java.util.concurrent.TimeoutException

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by ashish on 2/5/17.
  */
class TweetDispatcherTest extends FunSuite {

  /**
    * Testing whether we are able to get some tweets
    */
  test("Testing whether getTweetsByHashTag gets any tweets") {
    val tweetDispatcher = new TweetDispatcher
    val tweetsList = Await.result(tweetDispatcher.getTweetsByHashTag("#noida"), 25 seconds)

    assert(tweetsList.nonEmpty)
  }

  /**
    * This test will be passed as #raees is trending topic and getting all
    * the tweets of this hashtag in 2 seconds is not possible
    */
  test("Testing the - TimeoutException") {
    intercept[TimeoutException] {
      val tweetDispatcher = new TweetDispatcher
      val tweetsList = Await.result(tweetDispatcher.getTweetsByHashTag("#raees"), 2 seconds)
    }
  }

  /**
    * This test will pass only if getCount method's returned value is equal to
    * total number of tweets we get from twitter
    */
  test("Testing whether we get all the tweets in number equal to returned by getCount") {

    val tweetDispatcher = new TweetDispatcher
    val tweetsList = Await.result(tweetDispatcher.getTweetsByHashTag("#noida"), 30 seconds)
    val tweetsCount = Await.result(tweetDispatcher.getCountOfTweets("#noida"), 30 seconds)

    assert(tweetsCount == tweetsList.length)
  }

  /**
    * Testing the no of tweets per day
    */
  test("Testing whether we get the correct average tweet per day") {
    val tweetDispatcher = new TweetDispatcher
    val tweetsCount = Await.result(tweetDispatcher.getCountOfTweets("#baraut"), 25 seconds)
    val tweetsAverage = Await.result(tweetDispatcher.getAverageOfTweetsInOneDay("#baraut"), 25 seconds)

    assert(tweetsCount.toFloat / 3 == tweetsAverage)
  }

  /**
    * Testing the average like per tweet for a hashtag
    */
  test("Testing whether we get correct average likes per tweet") {
    val tweetDispatcher = new TweetDispatcher
    val tweetAverageLikes = Await.result(tweetDispatcher.getAverageLikes("#baraut"), 25 seconds)
    assert(tweetAverageLikes > 0)
  }

  /**
    * Testing the average retweet per tweet for a hashtag
    */
  test("Testing whether we get correct average retweets per tweet") {
    val tweetDispatcher = new TweetDispatcher
    val tweetAverageRetweets = Await.result(tweetDispatcher.getAverageRetweets("#baraut"), 25 seconds)
    assert(tweetAverageRetweets > 0)
  }

}
