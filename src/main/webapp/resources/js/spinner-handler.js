function showSpin() {
    var opts = {
        lines: 16, // Число линий для рисования
        length: 0, // Длина каждой линии
        width: 10, // Толщина линии
        radius: 30, // Радиус внутреннего круга
        corners: 1, // Скругление углов (0..1)
        rotate: 0, // Смещение вращения
        direction: 1, // 1: по часовой стрелке, -1: против часовой стрелки
        color: '#FF4500', // #rgb или #rrggbb или массив цветов
        speed: 1.2, // Кругов в секунду
        trail: 15, // Послесвечение
        shadow: false, // Тень(true - да; false - нет)
        hwaccel: false, // Аппаратное ускорение
        className: 'spinner', // CSS класс
        zIndex: 2e9, // z-index (по-умолчанию 2000000000)
        top: '50%', // Положение сверху относительно родителя
        left: '50%' // Положение слева относительно родителя
    };
    var target = document.getElementById('action-block');
    var div = document.createElement('div');
    div.className = "spinner";
    target.appendChild(div);

    var spinner = new Spinner(opts).spin();
    div.appendChild(spinner.el)

    $("#action-block").modal('show');
}

function stopSpin() {
    var spinner = $("#spinner");

    if (spinner != null) {
        spinner.remove();
    }
}