#!/bin/bash

if [ "${GITHUB_EVENT_ACTION}" == "opened" ]; then
  MESSAGE=":tada: *New Pull Request Opened* :tada:\n"
  MESSAGE+="*Title:* ${GITHUB_EVENT_PULL_REQUEST_TITLE}\n"
  MESSAGE+="*Author:* ${GITHUB_EVENT_PULL_REQUEST_USER_LOGIN}\n"
  MESSAGE+="*URL:* ${GITHUB_EVENT_PULL_REQUEST_HTML_URL}\n"
elif [ "${GITHUB_EVENT_ACTION}" == "closed" ]; then
  if [ "${GITHUB_EVENT_PULL_REQUEST_MERGED}" == "true" ]; then
    MESSAGE=":white_check_mark: *Pull Request Merged* :white_check_mark:\n"
  else
    MESSAGE=":x: *Pull Request Closed* :x:\n"
  fi
  MESSAGE+="*Title:* ${GITHUB_EVENT_PULL_REQUEST_TITLE}\n"
  MESSAGE+="*Author:* ${GITHUB_EVENT_PULL_REQUEST_USER_LOGIN}\n"
  MESSAGE+="*URL:* ${GITHUB_EVENT_PULL_REQUEST_HTML_URL}\n"
else
  MESSAGE="*Pull Request Updated*\n"
  MESSAGE+="*Title:* ${GITHUB_EVENT_PULL_REQUEST_TITLE}\n"
  MESSAGE+="*Author:* ${GITHUB_EVENT_PULL_REQUEST_USER_LOGIN}\n"
  MESSAGE+="*URL:* ${GITHUB_EVENT_PULL_REQUEST_HTML_URL}\n"
fi

echo "::set-output name=message::$MESSAGE"