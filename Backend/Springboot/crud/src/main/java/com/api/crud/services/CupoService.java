package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.dto.request.*;
import com.api.crud.models.*;
import com.api.crud.repositories.*;
import com.api.crud.services.models.EmailCupo;

import jakarta.mail.MessagingException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CupoService {


    
    @Autowired
    private ICupoRepository cupoRepository;

    @Autowired
    private ICupoOfflineRepository cupoOfflineRepository;

    @Autowired
    private IParqueaderoRepository parqueaderoRepository;

    @Autowired
    private TarifaService tarifaService;

    @Autowired
    private TarjetaCreditoService tarjetaCreditoService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaOfflineService facturaOfflineService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private UsuarioService usuarioService;

    public Map<String, Object> reservarCupo(ReservarCupoRequest request) throws MessagingException {
        boolean disponibilidad = verificarDisponibilidadCupo(request.getParqueaderoId(), request.getVehiculoId(), request.getHora_llegada());
        if (disponibilidad) {
            CupoModel cupo = new CupoModel();
            cupo.setEstado(CupoModel.Estado.RESERVADO);
            cupo.setUsuario_fk(request.getUsuarioId());
            cupo.setParqueadero_fk(request.getParqueaderoId());
            cupo.setVehiculo_fk(request.getVehiculoId());
            cupo.setFecha_creacion(ManejarFechas.obtenerFechaActual());
            cupo.setHora_llegada(request.getHora_llegada());
            cupo.setHoras_pedidas(request.getHoras());
            cupo.setPagado(false);
            cupo.setActivo(true);
            String codigo = Codigos.generarCodigoCupo();
            cupo.setCodigo(codigo);
            guardarCupo(cupo);
            EmailCupo emailCupo = new EmailCupo();
            emailCupo.setAsunto("Confirmación de Reserva de Parqueadero y Código de Acceso");
            if(usuarioService.getPorId(request.getUsuarioId()).isPresent()){
                emailCupo.setDestinatario(usuarioService.getPorId(request.getUsuarioId()).get().getCorreo());
            }
            emailCupo.setCodigo(codigo);
            emailCupo.setHoraLlegada(request.getHora_llegada());
            emailCupo.setHorasSolicitadas(request.getHoras());
            emailService.enviarCorreoCodigoCupo(emailCupo);
            
            return Map.of("data", Map.of("codigo", codigo), "msg", "Cupo reservado con exito");
        }
        return Map.of("data", "", "msg", "Sin disponibilidad");
    }

    public Map<String, Object> ocuparCupo(OcuparRequest request) throws MessagingException {
        boolean ocupado = ocuparCupo(request.getCodigo());
        if (ocupado) {
            EmailCupo emailCupo = new EmailCupo();
            emailCupo.setAsunto("Confirmación de Reserva de Parqueadero y Código de Acceso");
            if(usuarioService.getPorId(buscarCodigo(request.getCodigo()).getUsuario_fk()).isPresent()){
                emailCupo.setDestinatario(usuarioService.getPorId(buscarCodigo(request.getCodigo()).getUsuario_fk()).get().getCorreo());
            }
            emailCupo.setCodigo(request.getCodigo());
            emailCupo.setHoraLlegada(ManejarFechas.obtenerFechaActual());
            emailService.enviarCorreoConfirmacionCupo(emailCupo);
            return Map.of("data", Map.of("ocupado", true), "msg", "Cupo ocupado con exito");
        } else {
            return Map.of("data", "", "msg", "No se encontraron cupos con reserva");
        }
    }

    public Map<String, Object> finalizarCupoOn(OcuparRequest request) {
        FacturaModel factura = finalizarCupoOnline(request.getCodigo());
        if (factura != null) {
            return Map.of("data", Map.of("valor_ordinario", factura.getValorOrdinario(), "valor_extraordinario", factura.getValorExtraordinario(), "valor_total", factura.getValorTotal()), "msg", "Cupo finalizado con exito");
        }
        return Map.of("data", "", "msg", "Error al finalizar el cupo");
    }

    public Map<String, Object> finalizarCupoOff(OcuparRequest request) {
        FacturaOfflineModel factura = finalizarCupoOffline(request.getCodigo());
        if (factura != null) {
            return Map.of("data", Map.of("valor_total", factura.getValorPagado()), "msg", "Cupo finalizado con exito");
        }
        return Map.of("data", "", "msg", "Error al finalizar el cupo");
    }

    public Map<String, Object> cancelarCupo(CancelReservationRequest request) {
        boolean cancelado = cancelarReserva(request.getCupoId());
        if (cancelado) {
            return Map.of("data", Map.of("cancelado", true), "msg", "Cancelado con exito");
        } else {
            return Map.of("data", Map.of("cancelado", false), "msg", "Error al cancelar el cupo");
        }
    }

    public Map<String, Object> verificarDisponibilidad(VerificarDisponibilidadRequest verificar) {
        boolean cupoDisponible = verificarDisponibilidadCupo(verificar.getParqueaderoId(), verificar.getVehiculoId(), verificar.getHora_llegada());
        return Map.of("data", cupoDisponible, "msg", "Disponibilidad");
    }

    public CupoModel guardarCupo(CupoModel cupo) {
        return cupoRepository.save(cupo);
    }

    public CupoOfflineModel guardarCupoOffline(CupoOfflineModel cupo) {
        return cupoOfflineRepository.save(cupo);
    }

    public CupoModel buscarCodigo(String codigo) {
        return cupoRepository.findByCodigo(codigo).get();
    }

    public boolean ocuparCupo(String codigo) {
        Optional<CupoModel> cupoReservado = cupoRepository.findByCodigoAndEstado(codigo, CupoModel.Estado.RESERVADO);
        if (cupoReservado.isPresent()) {
            cupoReservado.get().setEstado(CupoModel.Estado.OCUPADO);
            cupoRepository.save(cupoReservado.get());
            actualizarParqueadero(cupoReservado.get().getParqueadero_fk(), cupoReservado.get().getVehiculo_fk(), 1);
            return true;
        }
        return false;
    }

    public FacturaModel finalizarCupoOnline(String codigo) {
        Optional<CupoModel> cupoOnline = cupoRepository.findByCodigo(codigo);
        if (cupoOnline.isPresent()) {
            cupoOnline.get().setHora_salida(ManejarFechas.obtenerFechaActual());
            cupoOnline.get().setEstado(CupoModel.Estado.FINALIZADO);
            actualizarParqueadero(cupoOnline.get().getParqueadero_fk(), cupoOnline.get().getVehiculo_fk(), -1);
            FacturaModel facturaModel = realizarFacturaOnline(cupoOnline.get());
            cupoRepository.save(cupoOnline.get());
            return facturaModel;
        }
        return null;
    }

    public FacturaOfflineModel finalizarCupoOffline(String codigo) {
        Optional<CupoOfflineModel> cupoOffline = cupoOfflineRepository.findByCodigo(codigo);
        if (cupoOffline.isPresent()) {
            cupoOffline.get().setHora_salida(ManejarFechas.obtenerFechaActual());
            cupoOffline.get().setEstado(CupoOfflineModel.Estado.FINALIZADO);
            actualizarParqueadero(cupoOffline.get().getParqueaderoFk(), cupoOffline.get().getVehiculoFk(), -1);
            FacturaOfflineModel factura = realizarFacturaOffline(cupoOffline.get());
            cupoOfflineRepository.save(cupoOffline.get());
            return factura;
        }
        return null;
    }

    public boolean cancelarReserva(Long cupoId) {
        Optional<CupoModel> cupoReservado = cupoRepository.findByIdAndEstado(cupoId, CupoModel.Estado.RESERVADO);
        if (cupoReservado.isPresent()) {
            cupoReservado.get().setEstado(CupoModel.Estado.CANCELADO);
            cupoRepository.save(cupoReservado.get());
            return true;
        }
        return false;
    }

    public boolean verificarDisponibilidadCupo(Long parqueaderoId, Long vehiculoId, Date horaLlegada) {
        List<CupoModel> cuposReservados = cupoRepository.findByParqueaderoAndVehiculoReservado(parqueaderoId, vehiculoId);
        Date horaLlegadaEvaluar;
        Date horaSalidaEvaluar;
        int horasPedidas;
        int cuposUtilizados = 0;
        for (CupoModel cupo : cuposReservados) {
            horaLlegadaEvaluar = cupo.getHora_llegada();
            horasPedidas = cupo.getHoras_pedidas();
            horaSalidaEvaluar = ManejarFechas.sumarHoras(horaLlegadaEvaluar, horasPedidas);
            if (horaLlegada.before(horaSalidaEvaluar) || horaLlegada.after(horaLlegadaEvaluar) || horaLlegada.equals(horaLlegadaEvaluar)) {
                cuposUtilizados += 1;
            }
        }

        cuposUtilizados += cupoRepository.findByParqueaderoAndVehiculoOcupado(parqueaderoId, vehiculoId).size();
        cuposUtilizados += cupoOfflineRepository.findByParqueaderoAndVehiculoOcupado(parqueaderoId, vehiculoId).size();

        Optional<ParqueaderoModel> parqueadero = parqueaderoRepository.findById(parqueaderoId);
        String tipoVehiculo = cupoRepository.findVehicleTypeById(vehiculoId);
        int cupoTotal = 0;

        switch (tipoVehiculo.toUpperCase()) {
            case "CARRO":
                cupoTotal = parqueadero.get().getCupo_carro_total();
                break;
            case "MOTO":
                cupoTotal = parqueadero.get().getCupo_moto_total();
                break;
            case "BICICLETA":
                cupoTotal = parqueadero.get().getCupo_bici_total();
                break;
        }

        return cuposUtilizados < cupoTotal;
    }

    public void actualizarParqueadero(Long parqueaderoId, Long vehiculoId, int cantidad) {
        ParqueaderoModel parqueadero = parqueaderoRepository.findById(parqueaderoId).get();
        String tipoVehiculo = cupoRepository.findVehicleTypeById(vehiculoId);

        switch (tipoVehiculo.toUpperCase()) {
            case "CARRO":
                parqueadero.setCupo_uti_carro(parqueadero.getCupo_uti_carro() + cantidad);
                break;
            case "MOTO":
                parqueadero.setCupo_uti_moto(parqueadero.getCupo_uti_moto() + cantidad);
                break;
            case "BICICLETA":
                parqueadero.setCupo_uti_bici(parqueadero.getCupo_uti_bici() + cantidad);
                break;
        }
        parqueaderoRepository.save(parqueadero);
    }

    private FacturaModel realizarFacturaOnline(CupoModel cupo) {
        FacturaModel factura = new FacturaModel();
        if (tarifaService.obtenerTarifaParqueaderoVehiculo(cupo.getParqueadero_fk(), cupo.getVehiculo_fk()).isPresent()){
            TarifaModel tarifa = tarifaService.obtenerTarifaParqueaderoVehiculo(cupo.getParqueadero_fk(), cupo.getVehiculo_fk()).get();
        }
        TarjetaCreditoModel tarjeta = tarjetaCreditoService.obtenerTarjetas(cupo.getUsuario_fk()).get(0);
    
        BigDecimal valorOrdinario = BigDecimal.valueOf(CalculoPrecioService.CalcularPrecio(tarifa, cupo.getHoras_pedidas()));
        Date horaSalidaSolicitada = ManejarFechas.sumarHoras(cupo.getHora_llegada(), cupo.getHoras_pedidas());
        BigDecimal valorExtraordinario = BigDecimal.valueOf(0);
        if (horaSalidaSolicitada.before(cupo.getHora_salida())) {
            valorExtraordinario = CalculoPrecioService.CalcularPrecioExtraOrdinario(tarifa, horaSalidaSolicitada, cupo.getHora_salida());
        }
        BigDecimal valorTotal = valorOrdinario.add(valorExtraordinario);
    
        factura.setValorOrdinario(valorOrdinario);
        factura.setValorExtraordinario(valorExtraordinario);
        factura.setValorTotal(valorTotal);
        factura.setCupoId(cupo.getId());
        factura.setVehiculoId(cupo.getVehiculo_fk());
        factura.setParqueaderoId(cupo.getParqueadero_fk());
        factura.setUsuarioId(cupo.getUsuario_fk());
        factura.setTarjetaCreditoId(tarjeta.getId());
        factura.setFechaCreacion(ManejarFechas.obtenerFechaActual());
        factura.setActivo(true);
        FacturaModel facturaFinal = facturaService.guardarFactura(factura);
    
        return facturaFinal;
    }
    
    private FacturaOfflineModel realizarFacturaOffline(CupoOfflineModel cupo) {
        FacturaOfflineModel factura = new FacturaOfflineModel();
        if(tarifaService.obtenerTarifaParqueaderoVehiculo(cupo.getParqueaderoFk(), cupo.getVehiculoFk()).isPresent()){
            TarifaModel tarifa = tarifaService.obtenerTarifaParqueaderoVehiculo(cupo.getParqueaderoFk(), cupo.getVehiculoFk()).get();
        }
        BigDecimal valorTotal = CalculoPrecioService.CalcularPrecioOffline(tarifa, cupo.getHoraLlegada(), cupo.getHoraSalida());
        factura.setValorPagado(valorTotal);
        factura.setCupoOfflineId(cupo.getId());
        factura.setVehiculoId(cupo.getVehiculoFk());
        factura.setParqueaderoId(cupo.getParqueaderoFk());
        factura.setFechaCreacion(ManejarFechas.obtenerFechaActual());
        factura.setActivo(true);
        FacturaOfflineModel facturaFinal = facturaOfflineService.guardarFactura(factura);
    
        return facturaFinal;
    }
    

        public Map<String, Object> reservarCupoOffline(ReservarCupoOfflineRequest request) {
        boolean disponibilidad = verificarDisponibilidadCupo(request.getParqueaderoId(), request.getVehiculoId(), ManejarFechas.obtenerFechaActual());
        if (disponibilidad) {
            CupoOfflineModel cupoOffline = new CupoOfflineModel();
            cupoOffline.setEstado(CupoOfflineModel.Estado.OCUPADO);
            cupoOffline.setParqueaderoFk(request.getParqueaderoId());
            cupoOffline.setVehiculoFk(request.getVehiculoId());
            cupoOffline.setFecha_creacion(ManejarFechas.obtenerFechaActual());
            cupoOffline.setHoraLlegada(ManejarFechas.obtenerFechaActual());
            cupoOffline.setActivo(true);
            String codigo = Codigos.generarCodigoCupo();
            cupoOffline.setCodigo(codigo);
            actualizarParqueadero(request.getParqueaderoId(), request.getVehiculoId(), 1);
            guardarCupoOffline(cupoOffline);
            return Map.of("data", Map.of("codigo", codigo), "msg", "Cupo reservado con éxito");
        }
        return Map.of("data", "", "msg", "Sin disponibilidad");
    }
}
