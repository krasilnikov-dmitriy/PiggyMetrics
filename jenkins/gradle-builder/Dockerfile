FROM java:8-jdk
MAINTAINER Dmitry Krasilnikov <krasilnikov.dmitriy@gmail.com>

# Gradle
ENV GRADLE_VERSION 3.2.1
ENV GRADLE_SHA 9843a3654d3e57dce54db06d05f18b664b95c22bf90c6becccb61fc63ce60689

RUN useradd -u 1000 -m -s /bin/bash jenkins

RUN cd /usr/lib \
 && curl -fl https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle-bin.zip \
 && echo "$GRADLE_SHA gradle-bin.zip" | sha256sum -c - \
 && unzip "gradle-bin.zip" \
 && ln -s "/usr/lib/gradle-${GRADLE_VERSION}/bin/gradle" /usr/bin/gradle \
 && rm "gradle-bin.zip"

# Set Appropriate Environmental Variables
ENV GRADLE_HOME /usr/lib/gradle
ENV PATH $PATH:$GRADLE_HOME/bin

VOLUME ["/home/jenkins/workspace"]