window.onload = function () {

    var alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
        't', 'u', 'v', 'w', 'x', 'y', 'z'];

    // Stored geusses
    var geusses = [ ];
    // Get elements
    var showLives = document.getElementById("mylives");

    //pagina selecione a dificuldade
    pageDificult = function () {
        dificuldadeButtons.removeAttribute("class", "oculto");
        jogo.setAttribute("class", "oculto");
        infos.setAttribute("class", "oculto");
        perdeu.setAttribute("class", "oculto");
        ganhou.setAttribute("class", "oculto");
    }

    pageDificult();

    // create alphabet ul
    var buttons = function () {
        myButtons = document.getElementById('buttons');
        letters = document.createElement('ul');

        for (var i = 0; i < alphabet.length; i++) {
            letters.id = 'alphabet';
            list = document.createElement('li');
            list.id = 'letter';
            list.innerHTML = alphabet[i];
            check();
            myButtons.appendChild(letters);
            letters.appendChild(list);
        }
    }

    // Create geusses ul
    result = function (word) {
        wordHolder = document.getElementById('hold');
        correct = document.createElement('ul');

        for (var i = 0; i < word.length; i++) {
            correct.setAttribute('id', 'my-word');
            guess = document.createElement('li');
            guess.setAttribute('class', 'guess');
            if (word[i] === "-") {
                guess.innerHTML = "-";
            } else {
                guess.innerHTML = "_";
            }

            geusses.push(guess);
            wordHolder.appendChild(correct);
            correct.appendChild(guess);
        }
    }

    // Show lives
    comments = function (vidas, status) {
        showLives.innerHTML = "Você tem " + vidas + " tentativas";
        if (status == "perdeu") {
            showLives.innerHTML = "Você perdeu";
            jogo.setAttribute("class", "oculto");
            perdeu.removeAttribute("class", "oculto");
        } else if (status == "ganhou"){
            showLives.innerHTML = "Você ganhou!";
            jogo.setAttribute("class", "oculto");
            ganhou.removeAttribute("class", "oculto");
        }
    }

    // Animate man
    var animate = function (vidas) {
        drawArray[vidas]();
    }

    // Hangman
    canvas =  function(){
        myStickman = document.getElementById("stickman");
        context = myStickman.getContext('2d');
        context.beginPath();
        context.strokeStyle = "#fff";
        context.lineWidth = 2;
    };

    // OnClick Function
    check = function () {
        list.onclick = function () {
            var letra = (this.innerHTML);
            this.setAttribute("class", "active");
            this.onclick = null;
            $.ajax({
                type: "POST",
                data: {letra: letra},
                url: "/letra",
                success: function (data) {
                    word = data.palavra
                    for (var i = 0; i < word.length; i++) {
                        if (word[i] === letra) {
                            geusses[i].innerHTML = letra;
                        }
                    }
                    if (data.ultLetraAcertou) {
                        comments(data.tentativasRestantes, data.situacao);
                    } else {
                        comments(data.tentativasRestantes, data.situacao);
                        animate(data.tentativasRestantes);
                    }
                },
                error: function (e) {
                    console.log("ERROR");
                    console.log(e)
                }
            });
        }
    }


    // Play
    play = function (word, vidas, situacao) {
        word = word.replace(/\s/g, "-");
        buttons();
        geusses = [ ];
        result(word);
        comments(vidas, situacao);
        canvas();
    }



    // Reset

    reiniciar = function () {
        jogo.removeAttribute("class", "oculto");
        correct.parentNode.removeChild(correct);
        letters.parentNode.removeChild(letters);
        myStickman = document.getElementById("stickman");
        context = myStickman.getContext('2d');
        context.clearRect(0, 0, 400, 400);

        pageDificult();
    }

    document.getElementById('reset').onclick = function() {
        reiniciar();
    }

    facil.onclick = function() {
        dificuldade("facil");
    };
    medio.onclick = function() {
        dificuldade("medio");
    };
    dificil.onclick = function() {
        dificuldade("dificil");
    };

    dificuldade = function (tipo) {
        this.onclick = null;
        $.ajax({
            type: "POST",
            data: {dificuldade: tipo},
            url: "/dificuldade",
            success: function (data) {
                dificuldadeButtons.setAttribute("class", "oculto");
                jogo.removeAttribute("class", "oculto");
                infos.removeAttribute("class", "oculto");

                jogoDaForca = JSON.parse(data);
                console.log(jogoDaForca.palavra);
                play(jogoDaForca.palavra,
                    jogoDaForca.tentativasRestantes,
                    jogoDaForca.situacao);
            },
            error: function (e) {
                console.log("ERROR");
                console.log(e)
            }
        });
    }

//REFACTOR

    //stickman
    draw = function($pathFromx, $pathFromy, $pathTox, $pathToy) {
        myStickman = document.getElementById("stickman");
        context = myStickman.getContext('2d');
        context.moveTo($pathFromx, $pathFromy);
        context.lineTo($pathTox, $pathToy);
        context.stroke();
    }

    head = function(){
        myStickman = document.getElementById("stickman");
        context = myStickman.getContext('2d');
        context.beginPath();
        context.arc(60, 25, 10, 0, Math.PI*2, true);
        context.stroke();
    }

    frame1 = function() {
        draw (0, 150, 150, 150);
    };

    frame2 = function() {
        draw (10, 0, 10, 600);
    };

    frame3 = function() {
        draw (0, 5, 70, 5);
    };

    frame4 = function() {
        draw (60, 5, 60, 15);
    };

    torso = function() {
        draw (60, 36, 60, 70);
    };

    rightArm = function() {
        draw (60, 46, 100, 50);
    };

    leftArm = function() {
        draw (60, 46, 20, 50);
    };

    rightLeg = function() {
        draw (60, 70, 100, 100);
    };

    leftLeg = function() {
        draw (60, 70, 20, 100);
    };

    drawArray = [rightLeg, leftLeg, rightArm, leftArm,  torso,  head, frame4, frame3, frame2, frame1];
}
