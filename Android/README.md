# News Reader - Kotlin/Android

Done in a [Coding Dojo](http://codingdojo.org/WhatIsCodingDojo/), 
8 sessions a 1.5/2 hours.

## Setup

Setup to TDD a simple UI.

This is a Kotlin [Gradle](https://gradle.org/) project. Run `./gradlew test` to run your tests.

Specific frameworks for testing Android UIs provided are [Espresso](https://developer.android.com/training/testing/espresso) and [Robolectric](http://robolectric.org/).

## Approach

1. Started to work on Overview, use MVP pattern.

2. Work on presenter `NewsReaderOverviewPresenter`. Can handle |

   - empty result and (DONE)
   - propagate found news. (DONE)

3. Needed creation of `model` classes `NewsItem` and `NewsModel`.

   - No logic so no tests for that right now.

4. Defined necessary methods in `view` = abstract UI `OverviewUi`.
5. Then work on real UI/view impl `NewsOverviewActivity`. ||

   - Begin with showing the header message. (DONE)
   - Should display nothing found. (DONE)
   - Should handle and display found news. (DONE)
   - Challenge: UI testing

6. Needed UI infrastructure `NewsAdapterTest` which we started as unit test.

7. Work on Backend, use DI.

8. Model, Download data. ||

   - change download API to RX |
   - Challenge: Async testing
   - Challenge: Web download testing with OK HTTP |

9. Application displays live data. WIN!

   - Challenge: test flag to avoid double init of application |

10. Continue UI with click of item, etc. |

