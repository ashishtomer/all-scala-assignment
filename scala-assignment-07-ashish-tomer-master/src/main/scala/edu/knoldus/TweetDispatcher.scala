package edu.knoldus

import twitter4j._
import twitter4j.conf.ConfigurationBuilder

import scala.collection.JavaConverters._
import scala.collection.immutable.List
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by ashish on 2/5/17.
  * This class is created to -
  * * Retrieve tweets  on the basis of HASHTAG(#hashtag)
  * * Find number of tweets
  * * Find average tweets per day
  * * Find average likes and re-tweets per tweet
  */
class TweetDispatcher {

  /**
    * This method is used to get all tweets by a #hashtag
    *
    * @param hashTag - The hashtag for searching tweets
    * @return - Lists of all tweets
    */
  def getTweetsByHashTag(hashTag: String): Future[List[Status]] = {

    Future {
      getTweets(TweetDispatcher.twitter, hashTag)
    }
  }

  /**
    * This is the logic method to get tweets for a hashtag
    *
    * @param twitter - a twitter4j.Twitter object to get tweets from Twitter
    * @param hashTag - The hashtag for searching tweets
    * @return - tweets for a hashtag
    */
  private def getTweets(twitter: twitter4j.Twitter, hashTag: String): List[Status] = {

    var query: Query = new Query(hashTag)
    query.setSince("2017-02-1") //It's hardcoded

    var result: QueryResult = twitter.search(query)

    var tweets: mutable.Buffer[Status] = ArrayBuffer[Status]()

    do {

      val tweetsOnCurrentPage = result.getTweets.asScala.toList
      for (tweet <- tweetsOnCurrentPage) {
        tweets += tweet
      }
      result = twitter.search(query)
      query = result.nextQuery()

    } while (query != null)

    tweets.toList
  }

  /**
    * This method is used to get total count of all tweets by a #hashtag
    *
    * @param hashTag - The hashtag for searching tweets
    * @return - Lists of all tweets
    */
  def getCountOfTweets(hashTag: String): Future[Long] = {

    Future {
      getCount(TweetDispatcher.twitter, hashTag)
    }
  }

  /**
    * This is the logic method to calculate count of tweets for a hashtag
    *
    * @param twitter - a twitter4j.Twitter object to get tweets from Twitter
    * @param hashTag - The hashtag for searching tweets
    * @return - count of tweets for a hashtag
    */
  private def getCount(twitter: twitter4j.Twitter, hashTag: String): Long = {
    var query: Query = new Query(hashTag)
    query.setSince("2017-02-1") //It's hardcoded

    var result: QueryResult = twitter.search(query)
    var tweets: mutable.Buffer[Status] = ArrayBuffer[Status]()
    var numberOfTweets: Long = 0

    do {
      numberOfTweets += result.getCount
      result = twitter.search(query)
      query = result.nextQuery()
    } while (query != null)

    numberOfTweets
  }

  /**
    * This method is used to get total average of tweets in one day by a #hashtag
    *
    * @param hashTag - The hashtag for searching tweets
    * @return - Lists of all tweets
    */
  def getAverageOfTweetsInOneDay(hashTag: String): Future[Float] = {

    Future {
      getCount(TweetDispatcher.twitter, hashTag) / 3
    }
  }

  /*---- Private methods ----*/

  /**
    * This method is used to get average of likes of all tweets by a #hashtag
    *
    * @param hashTag - The hashtag for searching tweets
    * @return - Lists of all tweets
    */
  def getAverageLikes(hashTag: String): Future[Float] = {
    Future {
      getAverageLikesLogic(TweetDispatcher.twitter, hashTag)
    }
  }

  /**
    * This is the logic method to calculate average likes per tweet for a hashtag
    *
    * @param twitter - a twitter4j.Twitter object to get tweets from Twitter
    * @param hashTag - The hashtag for searching tweets
    * @return - average likes per tweet
    */
  private def getAverageLikesLogic(twitter: twitter4j.Twitter, hashTag: String): Float = {
    var query: Query = new Query(hashTag)
    query.setSince("2017-02-1") //It's hardcoded

    var result: QueryResult = twitter.search(query)
    var tweets: mutable.Buffer[Status] = ArrayBuffer[Status]()
    var numberOfLikes: Long = 0
    var totalTweets: Long = 0

    do {
      val tweetsOnCurrentPage = result.getTweets.asScala.toList
      totalTweets += result.getCount

      for (tweet <- tweetsOnCurrentPage) {
        numberOfLikes += tweet.getFavoriteCount
      }

      result = twitter.search(query)
      query = result.nextQuery()
    } while (query != null)

    numberOfLikes.toFloat / totalTweets
  }

  /**
    * This method is used to get average of retweets of all tweets by a #hashtag
    *
    * @param hashTag - The hashtag for searching tweets
    * @return - Lists of all tweets
    */
  def getAverageRetweets(hashTag: String): Future[Float] = {
    Future {
      getAverageRetweetsLogic(TweetDispatcher.twitter, hashTag)
    }
  }

  /**
    * This is the logic method to calculate average retweet per tweet for a hashtag
    *
    * @param twitter - a twitter4j.Twitter object to get tweets from Twitter
    * @param hashTag - The hashtag for searching tweets
    * @return - average retweet per tweet
    */
  private def getAverageRetweetsLogic(twitter: twitter4j.Twitter, hashTag: String): Float = {
    var query: Query = new Query(hashTag)
    query.setSince("2017-02-1") //It's hardcoded

    var result: QueryResult = twitter.search(query)
    var tweets: mutable.Buffer[Status] = ArrayBuffer[Status]()
    var numberOfRetweets: Long = 0
    var totalTweets: Long = 0

    do {
      val tweetsOnCurrentPage = result.getTweets.asScala.toList
      totalTweets += result.getCount

      for (tweet <- tweetsOnCurrentPage) {
        numberOfRetweets += tweet.getRetweetCount
      }

      result = twitter.search(query)
      query = result.nextQuery()
    } while (query != null)

    numberOfRetweets.toFloat / totalTweets
  }

}

object TweetDispatcher {

  val builder: ConfigurationBuilder = new ConfigurationBuilder()

  builder.setDebugEnabled(true)
    .setOAuthConsumerKey("5RNzuMzyI8jQKmOjnNnPIXAHT")
    .setOAuthConsumerSecret("phm1AElxM879mQdh3UebgsS30A4WFjy4FS6gQDRk61fDWOByTi")
    .setOAuthAccessToken("828501750097248256-UnO1NGuvh6GaMR4jLUk3gvHtFrtcjub")
    .setOAuthAccessTokenSecret("OsNBhzJglpkKlEL3tXOah0F7qlEhX1nScnJwK9aFsR2wl")

  val twitterFactory = new TwitterFactory(TweetDispatcher.builder.build)

  val twitter: twitter4j.Twitter = TweetDispatcher.twitterFactory.getInstance()

}
