// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
    // "greetings"
    const greetings =
        ['I have 2600 hours on Team Fortress 2 for some godforsaken reason', 'valgrind is the root of all suffering', 'Here\'s an unfunny message', 'garlic design is my passion', 'You should play Celeste'];

    // Pick a random greeting.
    const greeting = greetings[Math.floor(Math.random() * greetings.length)];

    // Add it to the page.
    const greetingContainer = document.getElementById('greeting-container');
    greetingContainer.innerText = greeting;
}

// Shows a confirmation message upon submitting a feedback form
// Mostly copied from the server date example (sorry)
async function showConfirmation() {
    const responseFromServer = await fetch('/form-handler');
    const textFromResponse = await responseFromServer.text();

    const confirmContainer = document.getElementById('confirmContainer');
    confirmContainer.innerText = textFromResponse;
}

// Adds comments to the portfolio page
// I don't know what I'm doing ????
function loadComments() {
    fetch('/comment-display').then(response => response.json()).then((commentList) => {
        const commentListElement = document.getElementById('comment-list');
        commentList.forEach((comment) => {
            commentListElement.appendChild(createCommentElement(comment));
        })
    });
}

// Creates the elements to be added to the portfolio page
function createCommentElement(comment) {
    const commentElement = document.createElement('li');
    commentElement.className = 'comment';

    const nameElement = document.createElement('span');
    commentElement.innerText = comment.name;
    const messageElement = document.createElement('span');
    commentElement.innerText = comment.comment;

    commentElement.appendChild(nameElement);
    commentElement.appendChild(messageElement);
    return commentElement;
}