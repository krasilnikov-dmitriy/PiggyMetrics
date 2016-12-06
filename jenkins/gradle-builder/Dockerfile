FROM centos:7
MAINTAINER Krasilnikov Dmitry

# Install Essentials
RUN yum update -y && \
    yum clean all

# Install Packages
RUN yum install -y wget && \
    yum install -y unzip && \
	yum install -y java-1.8.0-openjdk-devel && \
	yum install -y sudo && \
	yum clean all

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
ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.111-1.b15.el7_2.x86_64/
ENV GRADLE_HOME /usr/lib/gradle
ENV PATH $PATH:$GRADLE_HOME/bin

COPY resolv.conf /etc/resolv.conf

VOLUME ["/home/jenkins/workspace"]