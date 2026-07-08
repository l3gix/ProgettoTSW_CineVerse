function setProvider(button, provider) {
            document.getElementById("provider").value = provider;
            document.getElementById("providerText").innerText = provider;

            let buttons = document.querySelectorAll(".provider-btn");

            buttons.forEach(function(btn) {
                btn.classList.remove("active");
            });

            button.classList.add("active");
        }
