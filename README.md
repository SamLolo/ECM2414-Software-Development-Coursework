# ECM2414 Software Development CA
## About The Test Suite
For our tests, we have chosen to use JUnit5.x. The Jar file for JUnit can be found in the [lib](./lib) directory.

The test class files for the project are stored in the [tests](./tests) folder. We have 6 test classes, one for each of the class files. The test suite also contains 4 pack text files that are used during the tests:
- [validPack.txt](./validPack.txt)
- [negativePack.txt](./negativePack.txt)
- [tooShortPack.txt](./tooShortPack.txt)
- [zeroPack.txt](./zeroPack.txt)

A compiled version of the source files and test source files can be found in [bin](./bin) directory, which can be used to run the tests with below. Compiling the tests can be done using the following command:
```cmd
javac -d bin/ -sourcepath src/ -cp lib/junit-platform-console-standalone-1.10.1.jar tests/*.java
```
## How To Run The Test Suite
The test suite can be executed through the command line, using the following command:
```cmd
java -jar lib/junit-platform-console-standalone-1.10.1.jar execute --classpath bin --scan-class-path
```
In total, there should be 29 tests, with an output as follows indicating the results of the test:
```cmd
Test run finished after 308 ms
[        25 containers found      ]
[         0 containers skipped    ]
[        25 containers started    ]
[         0 containers aborted    ]
[        25 containers successful ]
[         0 containers failed     ]
[        29 tests found           ]
[         0 tests skipped         ]
[        29 tests started         ]
[         0 tests aborted         ]
[        29 tests successful      ]
[         0 tests failed          ]
```