package com.fiap.saude_transparente.application.presenter;

public interface Presenter<TInput, TOutput> {
	TOutput presenter(TInput response);
}